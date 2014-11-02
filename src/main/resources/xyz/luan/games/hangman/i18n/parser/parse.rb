require 'fileutils'
require 'yaml'
require 'json'

def process_hash(translations, current_key, hash)
  hash.each do |new_key, value|
    combined_key = [current_key, new_key].delete_if { |k| k == '' }.join('.')
    if value.is_a?(Hash)
      process_hash(translations, combined_key, value)
    else
      translations[combined_key] = value
    end
  end
end

def to_json(yml_str)
  yml = YAML.load(yml_str)
  translations = {}
  process_hash(translations, '', yml)
  translations.to_json
end

def parse
  FileUtils.rm_rf('../*.json')

  Dir['../src/*'].each do |path|
    file_name = File.basename(path, '.*')
    File.open(path, 'r') do |original|
      json = to_json(original.read)
      File.open("../#{file_name}.json", 'w') do |parsed|
        parsed.write(json)
      end
    end
  end
end

parse
