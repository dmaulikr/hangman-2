require 'yaml'

def main
  map = {}
  Dir['*.png'].each do |file|
    parse(map, file.split('.').first)
  end
  map
end

def parse(map, name)
  parts = name.split('-')
  level = parts.pop
  color = parts.pop
  name = parts.join('-')

  map[name] ||= {}
  map[name][color] ||= []
  map[name][color].push(level)
end

def run
  map = main
  simple_map = {}
  all_colors = []
  map.each do |type, colors|
    simple_map[type] = []
    colors.each do |color, levels|
      unless levels.sort == ['1', '2', '3']
        throw 'Something is not right!'
      end
      simple_map[type].push(color)
      all_colors.push(color)
    end
  end
  puts 'levels'
  puts simple_map.to_yaml
  puts 'colors'
  puts all_colors.uniq.to_yaml
end

run
